#!/bin/bash

DEFECTS4J_AGXFAULTS=/defects4j_agxfaults
RUN_PROJECT_SCRIPT="$DEFECTS4J_AGXFAULTS/run_project_without_gen_config.sh"


#<project> <start_bug_id> <end_bug_id>

source ${RUN_PROJECT_SCRIPT} Chart 1 26 | tee ./log/Chart.txt

source ${RUN_PROJECT_SCRIPT} Cli 1 5 | tee ./log/Cli.txt
source ${RUN_PROJECT_SCRIPT} Cli 7 40 | tee -a ./log/Cli.txt

#------
source ${RUN_PROJECT_SCRIPT} Closure 1 62 | tee ./log/Closure.txt
source ${RUN_PROJECT_SCRIPT} Closure 64 92 | tee -a ./log/Closure.txt
source ${RUN_PROJECT_SCRIPT} Closure 94 176 | tee -a ./log/Closure.txt

#-------
source ${RUN_PROJECT_SCRIPT}  Codec 1 18 | tee ./log/Codec.txt

source ${RUN_PROJECT_SCRIPT}  Collections 25 28 | tee ./log/Collections.txt
source ${RUN_PROJECT_SCRIPT}  Compress 1 47 | tee ./log/Compress.txt
source ${RUN_PROJECT_SCRIPT}  Csv 1 16 | tee ./log/Csv.txt
source ${RUN_PROJECT_SCRIPT}  Gson 1 18 | tee ./log/Gson.txt
source ${RUN_PROJECT_SCRIPT}  JacksonCore 1 26 | tee ./log/JacksonCore.txt
source ${RUN_PROJECT_SCRIPT}  JacksonDatabind 1 112 | tee ./log/JacksonDatabind.txt
source ${RUN_PROJECT_SCRIPT}  JacksonXml 1 6 | tee ./log/JacksonXml.txt

#error: Jsoup. Bug in the defects4j!?
source ${RUN_PROJECT_SCRIPT}  Jsoup 1 93 | tee ./log/Jsoup.txt

source ${RUN_PROJECT_SCRIPT}  JxPath 1 22 | tee ./log/JxPath.txt

source ${RUN_PROJECT_SCRIPT}  Lang 1 1 | tee ./log/Lang.txt
source ${RUN_PROJECT_SCRIPT}  Lang 3 65 | tee -a ./log/Lang.txt

source ${RUN_PROJECT_SCRIPT}  Math 1 106 | tee ./log/Math.txt
source ${RUN_PROJECT_SCRIPT}  Mockito 1 38 | tee ./log/Mockito.txt

source ${RUN_PROJECT_SCRIPT}  Time 1 20 | tee ./log/Time.txt
source ${RUN_PROJECT_SCRIPT}  Time 22 27 | tee -a ./log/Time.txt


#//Compile error projects
#JacksonXml
#JacksonDatabind
#JacksonCore
#Math