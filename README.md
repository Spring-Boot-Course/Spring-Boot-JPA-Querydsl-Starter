# Spring-Boot-JPA-Querydsl-Starter

### build.gradle
```groovy
buildscript {
    ext {
        springBootVersion = '2.1.1.RELEASE'
        querydslPluginVersion = '1.0.10'
    }
    repositories {
        mavenCentral()
        maven { url "https://plugins.gradle.org/m2/" }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath("gradle.plugin.com.ewerk.gradle.plugins:querydsl-plugin:${querydslPluginVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

group = 'me.sml'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = 1.8

repositories {
    mavenCentral()
}


dependencies {

    compile("com.querydsl:querydsl-jpa") // querydsl
    compile("com.querydsl:querydsl-apt") // querydsl

    compile('org.springframework.boot:spring-boot-starter-data-jpa')
    compile('org.springframework.boot:spring-boot-starter-web')

    runtimeOnly('com.h2database:h2')
    compile('org.projectlombok:lombok')
    testImplementation('org.springframework.boot:spring-boot-starter-test')
}

// querydsl 적용
apply plugin: "com.ewerk.gradle.plugins.querydsl" // Plugin 적용
def querydslSrcDir = 'src/main/generated' // QClass 생성 위치

querydsl {
    library = "com.querydsl:querydsl-apt"
    jpa = true
    querydslSourcesDir = querydslSrcDir
}

sourceSets {
    main {
        java {
            srcDirs = ['src/main/java', querydslSrcDir]
        }
    }
}
```
> 중요한 것은 dependencies의 implementation과 compileOnly를 compile로 바꿔줘야 작동한다는 것! 

### Java Config

```java
@Configuration
public class QuerydslConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
```

위 설정으로 JPAQueryFactory를 주입받아 Querydsl 사용이 가능해진다.

### Querydsl 컴파일 하기
![1](https://t1.daumcdn.net/cfile/tistory/9925CC405C2A15D70C)

