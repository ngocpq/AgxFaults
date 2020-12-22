#!/bin/bash

DEFECTS4J_AGXFAULTS=/defects4j_agxfaults
RUN_PROJECT_SCRIPT="$DEFECTS4J_AGXFAULTS/run_project_without_gen_config.sh"


#<project> <start_bug_id> <end_bug_id>

source ${RUN_PROJECT_SCRIPT} Chart 5 5 | tee ./log/Chart5.txt
source ${RUN_PROJECT_SCRIPT} Chart 7 7 | tee ./log/Chart7.txt
source ${RUN_PROJECT_SCRIPT} Chart 18 18 | tee ./log/Chart18.txt
source ${RUN_PROJECT_SCRIPT} Chart 22 22 | tee ./log/Chart22.txt

#-------
source ${RUN_PROJECT_SCRIPT}  Codec 16 16 | tee ./log/Codec16.txt
source ${RUN_PROJECT_SCRIPT}  Codec 18 18 | tee ./log/Codec16.txt

source ${RUN_PROJECT_SCRIPT}  Compress 24 24 | tee ./log/Compress24.txt
source ${RUN_PROJECT_SCRIPT}  Compress 27 27 | tee ./log/Compress27.txt
source ${RUN_PROJECT_SCRIPT}  Compress 6 6 | tee ./log/Compress6.txt

source ${RUN_PROJECT_SCRIPT}  Csv 2 2 | tee ./log/Csv2.txt
source ${RUN_PROJECT_SCRIPT}  Csv 8 8 | tee ./log/Csv8.txt

source ${RUN_PROJECT_SCRIPT}  Lang 14 14 | tee ./log/Lang14.txt
source ${RUN_PROJECT_SCRIPT}  Lang 21 22 | tee ./log/Lang.txt
source ${RUN_PROJECT_SCRIPT}  Lang 30 31 | tee -a ./log/Lang.txt
source ${RUN_PROJECT_SCRIPT}  Lang 40 40 | tee -a ./log/Lang.txt
source ${RUN_PROJECT_SCRIPT}  Lang 58 58 | tee -a ./log/Lang.txt

source ${RUN_PROJECT_SCRIPT}  Math 94 94 | tee ./log/Math.txt
source ${RUN_PROJECT_SCRIPT}  Math 97 97 | tee -a ./log/Math.txt

source ${RUN_PROJECT_SCRIPT}  Mockito 11 11 | tee ./log/Mockito.txt
source ${RUN_PROJECT_SCRIPT}  Mockito 21 21 | tee -a ./log/Mockito.txt

#//Compile error projects
#JacksonXml
#JacksonDatabind
#JacksonCore
#Math