[//]: # (title: AWS Elastic Beanstalk)

<microformat>
<p>
<control>Initial project</control>: <a href="https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/embedded-server">embedded-server</a> or 
<a href="https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/engine-main">engine-main</a>
</p>
<p>
<control>Final project</control>: <a href="https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/aws-elastic-beanstalk">aws-elastic-beanstalk</a>
</p>
</microformat>

In this tutorial, we'll show you how to prepare and deploy a Ktor application to AWS Elastic Beanstalk. You can use one of the following initial projects depending on the way used to [create a Ktor server](create_server.xml):
* [embedded-server](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/embedded-server)
* [engine-main](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/engine-main)

> Learn more about deploying Java applications from [Elastic Beanstalk docs](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_Java.html).


## Prerequisites {id="prerequisites"}
Before starting this tutorial, you need to create an AWS account.


## Clone a sample application {id="clone"}
To open a sample application, follow the steps below:

1. Clone a Ktor documentation repository and open the [codeSnippets](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets) project.
2. Open the [embedded-server](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/embedded-server) or [engine-main](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/engine-main) sample. These samples demonstrate different approaches to [creating and configuring a Ktor server](create_server.xml): in code or by using the `application.conf` configuration file. The only difference in deploying these projects is how to [specify a port](#port) used to listen for incoming requests.

## Prepare an application {id="prepare-app"}

### Step 1: Configure a port {id="port"}

First, you need to specify a port used to listen for incoming requests. Elastic Beanstalk forwards requests to your application on port 5000. Optionally, you can override the default port by setting the `PORT` environment variable. Depending on the way used to [configure a Ktor server](create_server.xml), you can configure a port in one of the following ways:
* If you've chosen the [embedded-server](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/embedded-server) sample with server configuration specified in code, you can obtain the environment variable value using `System.getenv` or use the default _5000_ value in a case an environment variable is not specified. Open the `Application.kt` file placed in the `src/main/kotlin/com/example` folder and change the `port` parameter value of the `embeddedServer` function as shown below:
   ```kotlin
   fun main() {
      embeddedServer(Netty, port = (System.getenv("PORT")?:"5000").toInt()) {
      // ...
      }.start(wait = true)
   }
    ```

* If you've chosen the [engine-main](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/engine-main) sample with server configuration specified in the `application.conf` file, you can assign the environment variable to the `port` parameter by using the `${ENV}` syntax. Open the `application.conf` file placed in `src/main/resources` and update it as shown below:
   ```
   ```
  {src="snippets/aws-elastic-beanstalk/src/main/resources/application.conf" lines="1-5,9" style="block"}

### Step 2: Apply the Shadow plugin {id="configure-shadow-plugin"}
This tutorial shows how to deploy the application to Elastic Beanstalk using a [fat JAR](fatjar.md). To generate fat JARs, you need to apply the Shadow plugin. Open the `build.gradle.kts` file and add the plugin to the `plugins` block:
```groovy
```
{src="snippets/aws-elastic-beanstalk/build.gradle.kts" lines="5,8-9"}

Then, make sure that the [main application class](server-dependencies.xml#create-entry-point) is configured:
```kotlin
```
{src="snippets/aws-elastic-beanstalk/build.gradle.kts" lines="11-13"}


## Build a Fat JAR {id="build"}
To build a Fat JAR, open the terminal and execute the `shadowJar` task provided by the [Shadow plugin](#configure-shadow-plugin):

<tabs group="os">
<tab title="Linux/MacOS" group-key="unix">
<code style="block" lang="Bash">./gradlew :aws-elastic-beanstalk:shadowJar</code>
</tab>
<tab title="Windows" group-key="windows">
<code style="block" lang="CMD">gradlew.bat :aws-elastic-beanstalk:shadowJar</code>
</tab>
</tabs>

When this build completes, you should see the `aws-elastic-beanstalk-all.jar` file in the `build/libs` directory.


## Deploy an application {id="deploy-app"}
To deploy the application, sign in to [AWS Management Console](https://aws.amazon.com/console/) and follow the steps below:
1. Open the **Elastic Beanstalk** service in the **AWS services** group.
2. On the opened page, click **Create Application**.
3. Specify the following application settings:
   * **Application name**: Specify the application name (for example, _Sample Ktor app_).
   * **Platform**: Choose _Java_ from the list.
   * **Platform branch**: Choose _Corretto 11 running on 64bit Amazon Linux 2_.
   * **Application code**: Select _Upload your code_.
   * **Source code origin**: Choose _Local file_. Then, click the **Choose file** button and choose the Fat JAR generated in the [previous step](#build). Wait until the file is uploaded.
4. Click the **Create application** button and wait several minutes until Beanstalk creates the environment and publishes the application:
   ```
   INFO    Instance deployment completed successfully.
   INFO    Application available at Samplektorapp-env.eba-bnye2kpu.us-east-2.elasticbeanstalk.com.
   INFO    Successfully launched environment: Samplektorapp-env
   ```
   {style="block"}