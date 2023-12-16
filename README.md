## GROOVY-11250  
https://issues.apache.org/jira/browse/GROOVY-11250

This project compiles in Groovy 3.0.19 but not in Groovy 3.0.20-SNAPSHOT.

```console
./gradlew -PgroovyVersion='3.0.20-SNAPSHOT' compileGroovy
```
```console
./gradlew -PgroovyVersion='3.0.19' compileGroovy
```