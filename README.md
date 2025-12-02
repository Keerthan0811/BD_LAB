Running hadoop

javac -d . *.java
echo Main-Class: __Folder__.driver > Manifest.txt
jar cfm __Folder__.jar Manifest.txt __Folder__/*.class
hadoop jar __Folder__.jar input.txt output
cat output/*



Running pyspark

