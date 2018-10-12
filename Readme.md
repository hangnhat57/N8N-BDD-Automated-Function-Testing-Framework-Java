# N∞N BDD Automated Functional Testing Framework 

Java Automation Test Helper for selenium

## Installation

- Register a repositories in maven file: 

```
 <repositories>
        <repository>
            <id>testutils-mvn-repo</id>
            <url>https://raw.github.com/hangnhat57/N8N-BDD-Automated-Function-Testing-Framework-Java/mvn-repo/</url>
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
            <artifactId>inu</artifactId>
            <version>1.1.8</version>
    </dependency>
``` 




## Usage

- Create Hook class Inheritance from tech.nhatnguyen.nihonsupittsu.HookHelper
- Implement method: TestInit() for @Before tag
- Implement method: TestTearDown for @After tag 
- Inheritance from tech.nhatnguyen.nihonsupittsu.PageObjectHelper to all defined Page classes.
- Inheritance from tech.nhatnguyen.nihonsupittsu.StepHelper to all defined Step classes.

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

MIT License

Copyright (c) 2018 Nhật Nguyễn

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
