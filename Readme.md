# Sherpherd Automation

Java Automation Test Helper for selenium

## Installation

- Register a repositories in maven file: 

```
 <repositories>
        <repository>
            <id>testutils-mvn-repo</id>
            <url>https://raw.github.com/hangnhat57/testutils/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
```

- Insert dependency :
```
    <dependency>
            <groupId>tech.nhatnguyen</groupId>
            <artifactId>shepherd</artifactId>
            <version>1.1.3</version>
    </dependency>
``` 




## Usage

- Create Hook class Inheritance from tech.nhatnguyen.initializer.HookHelper
- Implement method: TestInit() for @Before tag
- Implement method: TestTearDown for @After tag 
- Inheritance from tech.nhatnguyen.initializer.PageObjectHelper to all defined Page classes.
- Inheritance from tech.nhatnguyen.initializer.StepHelper to all defined Step classes.

Package info: 
- akitahelper: Selenium custom functions 
- kishuhelper: Email reader
- shikokuhelper: Datadriven custom function
- shibahelper: common function support such as convert String, read environment variables.



## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

nhatnguyen.tech - hangnhat57@gmail.com

## License

TODO