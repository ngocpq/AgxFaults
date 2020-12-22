#!/bin/bash

project=$1
start_bug_id=$2
end_bug_id=$3

echo "Run_project: " ${project} from ${start_bug_id} to ${end_bug_id}

export DEFECTS4J_HOME=/defects4j
export PATH=$PATH:${DEFECTS4J_HOME}/framework/bin

DEFECTS4J_AGXFAULTS=/defects4j_agxfaults
RUN_AGXFAULTS_SCRIPT="$DEFECTS4J_AGXFAULTS/run_agxfaults.sh"

output_dir=/tmp/defects4j_projects
config_root=${DEFECTS4J_AGXFAULTS}/jpfconfigs

if [ ! -d "$output_dir" ]; then
  mkdir -p $output_dir
fi

if [ ! -d "$config_root" ]; then
  mkdir -p $config_root
fi

oldIFS=$IFS

#chart

for bug_id in $(seq $start_bug_id $end_bug_id); do
	IFS=$oldIFS
	cd /
 
	echo "--- checking out ${project} $bug_id ----"; 
	if [ ! -d "${output_dir}/${project}" ]; then	
	  mkdir -p ${output_dir}/${project}
	fi
	
	working_dir=${output_dir}/${project}/${project}${bug_id}
	if [ ! -d "${working_dir}" ]; then		  
		echo "Compiling ..."
		defects4j checkout -p ${project} -v ${bug_id}b -w ${working_dir}	
		cd ${working_dir}

		echo "Compiling ..."
		defects4j compile -w ${working_dir}	
	fi
	
	cd ${working_dir}
	
	bug_config_dir="${config_root}/${project}/${project}${bug_id}"
	test_config_dir="${bug_config_dir}/config"
	if [ ! -d "$test_config_dir" ]; then
		mkdir -p $test_config_dir
	fi

	result_output_dir="${config_root}/${project}/${project}${bug_id}/output"
	if [ ! -d "$result_output_dir" ]; then
		mkdir -p $result_output_dir
	fi

	for filename in ${test_config_dir}/*.jpf; do
		echo "START RUNNING TEST CASE: " $filename 		
		source ${RUN_AGXFAULTS_SCRIPT} ${filename}
		echo "FINISHED TEST CASE: " $filename 
	done	

	#-----------
	
	IFS=$oldIFS
	cd ~/
	#rm -rf ${working_dir}
done

IFS=$oldIFS

