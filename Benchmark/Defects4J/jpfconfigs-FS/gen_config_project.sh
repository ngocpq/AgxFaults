#!/bin/bash

#project=Chart
#BUG_COUNT=26
project=$1
start_bug_id=$2
end_bug_id=$3

echo "Run_project: " ${project} from ${start_bug_id} to ${end_bug_id}

export DEFECTS4J_HOME=/defects4j
export PATH=$PATH:${DEFECTS4J_HOME}/framework/bin

DEFECTS4J_AGXFAULTS=/defects4j_agxfaults

D4J_CHECKOUT_DIR=/tmp/defects4j_projects
if [ ! -d "$D4J_CHECKOUT_DIR" ]; then
  mkdir -p $D4J_CHECKOUT_DIR
fi

config_root=${DEFECTS4J_AGXFAULTS}/jpfconfigs
if [ ! -d "$config_root" ]; then
  mkdir -p $config_root
fi

output_root_dir=${DEFECTS4J_AGXFAULTS}/outputs
if [ ! -d "$output_root_dir" ]; then
  mkdir -p $output_root_dir
fi

oldIFS=$IFS

#write defects4j.jpf
#defects4j_jpf_filepath=${config_root}/defects4j.jpf
#echo "+classpath=\${sut_root_dir}/\${dir_bin_classes};\${sut_root_dir}/\${dir_bin_tests};\${cp_compile};\${cp_test};" > ${defects4j_jpf_filepath}
#echo "+sourcepath=\${dir_src_classes};\${sut_src_tests};"  >> ${defects4j_jpf_filepath}
#echo "@include=./config_junit.jpf" >> ${defects4j_jpf_filepath}

for bug_id in $(seq $start_bug_id $end_bug_id); do
	IFS=$oldIFS
	cd /
 
	echo "--- checking out ${project} $bug_id ----"; 
	if [ ! -d "${D4J_CHECKOUT_DIR}/${project}" ]; then	
	  mkdir -p ${D4J_CHECKOUT_DIR}/${project}
	fi

	sut_root_dir=${D4J_CHECKOUT_DIR}/${project}/${project}${bug_id}
	defects4j checkout -p ${project} -v ${bug_id}b -w ${sut_root_dir}	
	cd ${sut_root_dir}

	echo "Generate buggy config file..."
	
	bug_config_dir="${config_root}/${project}/${project}${bug_id}"
	test_config_dir="${bug_config_dir}/config"
	if [ ! -d "$test_config_dir" ]; then
		mkdir -p $test_config_dir
	fi

	#result_output_dir="${output_root_dir}/${project}/${project}${bug_id}/output"
	#if [ ! -d "$result_output_dir" ]; then
	#	mkdir -p $result_output_dir
	#fi
	
	project_jpf_filename="defects4j_${project}${bug_id}.jpf"
	project_jpf_filepath="${bug_config_dir}/${project_jpf_filename}"

	echo "@include=../../defects4j-config.jpf" > ${project_jpf_filepath}
	echo "" >> ${project_jpf_filepath}
	
	echo sut_root_dir="\${D4J_CHECKOUT_DIR}/${project}/${project}${bug_id}" >> ${project_jpf_filepath}
	echo dir_bin_classes="$(defects4j export -p dir.bin.classes)" >> ${project_jpf_filepath}
	echo dir_bin_tests="$(defects4j export -p dir.bin.tests)" >> ${project_jpf_filepath}
	echo dir_src_classes="$(defects4j export -p dir.src.classes)" >> ${project_jpf_filepath}
	echo dir_src_tests="$(defects4j export -p dir.src.tests)" >> ${project_jpf_filepath}
	
	#echo cp_compile="$(defects4j export -p cp.compile)" >> ${project_jpf_filepath}
	cp_compile="$(defects4j export -p cp.compile)"
	
	replace_pattern="$D4J_CHECKOUT_DIR/"
	replace_string="\${D4J_CHECKOUT_DIR}/"
	cp_compile="${cp_compile//$replace_pattern/$replace_string}" 
	
	replace_pattern="$DEFECTS4J_HOME/"
	replace_string="\${DEFECTS4J_HOME}/"
	cp_compile="${cp_compile//$replace_pattern/$replace_string}" 
	
	echo cp_compile="${cp_compile}" >> ${project_jpf_filepath}
	#-----
	
	#echo cp_test="$(defects4j export -p cp.test)" >> ${project_jpf_filepath}
	cp_test="$(defects4j export -p cp.test)"
	
	replace_pattern="$D4J_CHECKOUT_DIR/"
	replace_string="\${D4J_CHECKOUT_DIR}/"
	cp_test="${cp_test//$replace_pattern/$replace_string}" 
	
	replace_pattern="$DEFECTS4J_HOME/"
	replace_string="\${DEFECTS4J_HOME}/"
	cp_test="${cp_test//$replace_pattern/$replace_string}" 
	echo cp_test="$cp_test" >> ${project_jpf_filepath}
	
	echo "" >> ${project_jpf_filepath}
	echo "@include=../../defects4j.jpf" >> ${project_jpf_filepath}

	#-----------
	echo "Generating config file for testcases";	
	trigger_tests="$(defects4j export -p tests.trigger)"
		
	echo "Number failing tests of ${project}$bug_id is"
	echo "$trigger_tests" | wc -l
	echo "$trigger_tests"
	echo -------------

	test_count=0
	for testcase in ${trigger_tests}; do
		test_count="$((test_count+1))"
		echo "  $testcase"
		IFS=':'
		read -a strarr <<< "$testcase"
		echo length ${#strarr[@]}

		testclass="${strarr[0]}"
		testmethod="${strarr[2]}"
		echo "test${test_count}: ${project}${bug_id}_${testclass}.${testmethod}.jpf"
	
		testid="${project}${bug_id}_t${test_count}.${testmethod}"
		file_name="${testid}.jpf"		
		test_config_file="${test_config_dir}/${file_name}"		

		if [ -f $test_config_file ] ; then
			rm $test_config_file
		fi
		
		echo "testid=${testid}" > ${test_config_file}
		echo "testclass=${testclass}" >> ${test_config_file}
		echo "testmethod=${testmethod}" >> ${test_config_file}
		echo "jdart.log.output=\${config_path}/../output/\${testid}.jpf.log" >> ${test_config_file}
		echo "ffl.report.filepath=\${config_path}/../output/\${testid}.jpf.out" >> ${test_config_file}
		echo "" >> ${test_config_file}
		echo "@include=../${project_jpf_filename}" >> ${test_config_file}
		
	done
	
	IFS=$oldIFS
	cd ~/
	#rm -rf ${sut_root_dir}
done

IFS=$oldIFS

