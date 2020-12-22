
# build docker image
#docker build -t d4j_z3:agxfaults .

#starting docker container
docker run --name jpf -it -v /c/Docker/jpf-core-z3-v4.1/defects4j_agxfaults:/defects4j_agxfaults d4j_z3:agxfaults bash


docker run --rm --name jpf -it -v /c/Docker/jpf-core-z3-v4.1/experiment_defects4j_v2.0.0:/defects4j_agxfaults d4j_z3:agxfaults bash

docker run --rm --name jpf -it -v /c/Docker/jpf-core-z3-v4.1/experiment_defects4j_v2.0.0:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging/jpf-ffl:/jpf/jpf-ffl d4j_z3:agxfaults bash

docker run --rm --name jpf -it -v /c/Docker/jpf-core-z3-v4.1/experiment_defects4j_v2.0.0:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging/jpf-ffl:/jpf/jpf-ffl -v /c/Docker/jpf-core-z3-v4.1/defects4j_project_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash

docker run --rm --name jpf -it -v /c/workplace/agxfault_experiment/defects4j:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging/jpf-ffl:/jpf/jpf-ffl -v /c/Docker/jpf-core-z3-v4.1/defects4j_project_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash

docker run --rm --name jpf -it -v /c/workplace/agxfault_experiment/defects4j:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging/jpf-ffl:/jpf/jpf-ffl -v /c/workplace/agxfault_experiment/defects4j/defects4j_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash


docker run --rm --name jpf -it -v /c/workplace/agxfault_experiment/defects4j:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging/jpf-ffl:/jpf/jpf-ffl d4j_z3:agxfaults bash

docker run --rm --name jpf -it -v /h/agxfault_experiment/defects4j:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging/jpf-ffl:/jpf/jpf-ffl -v /h/defects4j_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash

docker run --rm --name jpf -it -v /h/agxfault_experiment/defects4j:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging:/jpf -v /h/defects4j_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash


docker run --rm --name jpf -it -v /h/D4jFaultLocation:/D4jFaultLocation d4j_z3:agxfaults bash

docker run --rm --name jpf -it -v /h/checkout:/tmp/demo d4j_z3:agxfaults bash

#AgxFaults: MucGuidedIncMaxSMT
docker run --rm --name jpf -it -v /h/agxfault_experiment/defects4j:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging:/jpf -v /h/defects4j_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash

#FlowSensitive
docker run --rm --name agxfault_FS -it -v /h/agxfault_experiment/defects4j_FlowSensitive:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging:/jpf -v /h/defects4j_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash

#FlowInSensitive
docker run --rm --name agxfault_FI -it -v /h/agxfault_experiment/defects4j_FlowInSensitive:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging:/jpf -v /h/defects4j_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash

#MucDrivenIncMaxSAT
docker run --rm --name agxfault_FI -it -v /h/agxfault_experiment/jpfconfigs-applsci-MucIncMaxSMT:/defects4j_agxfaults -v /e/Workspace/AutomatedDebugging:/jpf -v /h/defects4j_checkout:/tmp/defects4j_projects d4j_z3:agxfaults bash