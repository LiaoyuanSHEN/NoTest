# NoTest

NoTest is a test automation framework that requires NO testing code.

Its basic idea is to action method-level input/output as test vectors by running your application in real environment, and then playback the test vectors in the future (in either development environment or production environment) to do regression testing.

## Build

run gradle task `shadowJar`, then the output jar will be placed at `<project folder>/build/libs/no-test.jar`

## Demo Usage

### Only support linux & macOS

#### Recording

1. prepare an json file for which method you would like to record, using `com.github.zszlly.util.MethodUtils.toNoTestMethodDescription(Method)` to generate your method description and fill them to a json list.

    Here is an example:
    
        [
            "Lcom/github/zszlly/Tester;.add(II)I",
            "Lcom/github/zszlly/Tester;.addB(ILcom/github/zszlly/Tester$GetB;)I",
            "Lcom/github/zszlly/Tester;.addArray([Ljava/lang/Integer;)I",
            "Lcom/github/zszlly/Tester;.addAAndB(Lcom/github/zszlly/Tester$GetB;)I"
        ]

2. build the project as build section described.

3. copy the json properties file and built jar file `<project folder>/build/libs/no-test.jar` into the host where the demo program will run at.

4. run demo program in first terminal at the host like:

        java -cp "no-test.jar" com.github.zszlly.DummyMain

5. run `jps` to get your java program pid in second terminal.

6. run command in second terminal:
        
        java -Xbootclasspath/a:$JAVA_HOME/lib/tools.jar -DrecordMethod=<your configuration file path> -DcaseSavingPath=<the path where recorded files will be saved to> -jar no-test.jar <your process PID>

7. return to first terminal and press any key to continue the demo program.

8. you will find the latest 10 method calls for which you filled in your configuration.

#### Playback

you can now just add your project libraries as classpath and run the following command to mock and run the test cases:
    
    java -cp "no-test.jar:<your project classpath>" com.github.zszlly.player.NoTestPlayer <recorded file path>
