[//]: # (title: Content encoding)

<var name="artifact_name" value="ktor-client-encoding"/>

<microformat>
<p>
<b>Required dependencies</b>: <code>io.ktor:%artifact_name%</code>
</p>
<var name="example_name" value="client-content-encoding"/>
<include src="lib.xml" include-id="download_example"/>
</microformat>

<excerpt>
The ContentEncoding plugin allows you to enable specified compression algorithms (such as 'gzip' and 'deflate') and configure their settings.
</excerpt>

The Ktor client provides the [ContentEncoding](https://api.ktor.io/ktor-client/ktor-client-plugins/ktor-client-encoding/io.ktor.client.plugins.compression/-content-encoding/index.html) plugin that allows you to enable specified compression algorithms (such as `gzip` and `deflate`) and configure their settings. This plugin serves two primary purposes:
* Sets the `Accept-Encoding` header with the specified quality value.
* Decodes [content received from a server](response.md#body) to obtain the original payload.


## Add dependencies {id="add_dependencies"}
To use `ContentEncoding`, you need to include the `%artifact_name%` artifact in the build script:

<include src="lib.xml" include-id="add_ktor_artifact"/>
<include src="lib.xml" include-id="add_ktor_client_artifact_tip"/>

## Install ContentEncoding {id="install_plugin"}
To install `ContentEncoding`, pass it to the `install` function inside a [client configuration block](create-client.md#configure-client):
```kotlin
val client = HttpClient(CIO) {
    install(ContentEncoding)
}
```

## Configure ContentEncoding {id="configure_plugin"}
The [example](https://github.com/ktorio/ktor-documentation/tree/%current-branch%/codeSnippets/snippets/client-content-encoding) below shows how to enable the `deflate` and `gzip` encoders on the client with the specified quality values:

```kotlin
```
{src="snippets/client-content-encoding/src/main/kotlin/com/example/Application.kt" lines="13-18"}

If required, you can implement the `ContentEncoder` interface to create a custom encoder and pass it to the `customEncoder` function.
