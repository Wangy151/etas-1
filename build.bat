call mvn -Dmaven.test.skip=true clean package

if exist output (rmdir /s/q output)
md output\etas\bin
md output\etas\conf
md output\etas\log
md output\etas\template
md output\etas\upload

copy target\etas-1.0.jar output\etas\bin
rename output\etas\bin\etas-1.0.jar etas.jar
copy start.bat output\etas
copy target\classes\application.properties output\etas\conf
copy template\ETAS_import_student_info_template_download.xls output\etas\template

pause
