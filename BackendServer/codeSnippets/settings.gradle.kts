pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.google.cloud.tools.appengine")) {
                useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "ktor-codesnippets"

fun module(group: String, name: String) {
    include(name)
    project(":$name").projectDir = file("$group/$name")
}

// ---------------------------

module("snippets", "jetty-war")
module("snippets", "tomcat-war")
module("snippets", "auth-basic")
module("snippets", "auth-basic-hash-table")
module("snippets", "auth-digest")
module("snippets", "auth-form-html-dsl")
module("snippets", "auth-jwt-hs256")
module("snippets", "auth-jwt-rs256")
module("snippets", "auth-ldap")
module("snippets", "auth-oauth-google")
module("snippets", "custom-plugin-base-api")
module("snippets", "custom-plugin")
module("snippets", "guice")
module("snippets", "gson")
module("snippets", "http2-push")
module("snippets", "http2-netty")
module("snippets", "http2-jetty")
module("snippets", "html")
module("snippets", "html-templates")
module("snippets", "jackson")
module("snippets", "client-json")
module("snippets", "locations")
module("snippets", "dropwizard-metrics")
module("snippets", "upload-file")
module("snippets", "download-file")
module("snippets", "post")
module("snippets", "post-form-parameters")
module("snippets", "session-cookie-client")
module("snippets", "session-cookie-server")
module("snippets", "session-header-server")
module("snippets", "static-files")
module("snippets", "static-resources")
module("snippets", "timeout")
module("snippets", "autohead")
module("snippets", "autoreload-engine-main")
module("snippets", "autoreload-embedded-server")
module("snippets", "client-download-file")
module("snippets", "client-logging")
module("snippets", "client-logging-napier")
module("snippets", "embedded-server")
module("snippets", "embedded-server-modules")
module("snippets", "engine-main")
module("snippets", "engine-main-modules")
module("snippets", "freemarker")
module("snippets", "velocity")
module("snippets", "mustache")
module("snippets", "thymeleaf")
module("snippets", "client-upload")
module("snippets", "client-upload-progress")
module("snippets", "client-download-streaming")
module("snippets", "client-parallel-requests")
module("snippets", "client-timeout")
module("snippets", "client-retry")
module("snippets", "client-cookies")
module("snippets", "client-validate-2xx-response")
module("snippets", "client-validate-non-2xx-response")
module("snippets", "simulate-slow-server")
module("snippets", "e2e")
module("snippets", "google-appengine-standard")
module("snippets", "client-auth-basic")
module("snippets", "client-auth-digest")
module("snippets", "client-auth-oauth-google")
module("snippets", "caching-headers")
module("snippets", "micrometer-metrics")
module("snippets", "pebble")
module("snippets", "auth-form-session")
module("snippets", "aws-elastic-beanstalk")
module("snippets", "conditional-headers")
module("snippets", "ssl-embedded-server")
module("snippets", "ssl-engine-main")
module("snippets", "server-websockets")
module("snippets", "client-websockets")
module("snippets", "client-testing-mock")
module("snippets", "json-kotlinx")
module("snippets", "client-json-kotlinx")
module("snippets", "logging")
module("snippets", "call-id")
module("snippets", "cors-frontend")
module("snippets", "cors-backend")
module("snippets", "compression")
module("snippets", "client-content-encoding")
module("snippets", "client-caching")
module("snippets", "client-default-request")
module("snippets", "client-http-send")
module("snippets", "fatjar")
module("snippets", "tutorial-http-api")
module("snippets", "tutorial-website-interactive")
module("snippets", "tutorial-website-interactive-persistence")
module("snippets", "tutorial-website-interactive-docker-compose")
module("snippets", "tutorial-website-static")
module("snippets", "tutorial-websockets-server")
module("snippets", "tutorial-websockets-client")
module("snippets", "tutorial-client-get-started")
module("snippets", "server-websockets-serialization")
module("snippets", "client-websockets-serialization")
module("snippets", "resource-routing")
module("snippets", "client-type-safe-requests")
module("snippets", "webjars")
module("snippets", "client-submit-form")
module("snippets", "forwarded-header")
module("snippets", "status-pages")
module("snippets", "client-download-file-range")
module("snippets", "shutdown-url")
module("snippets", "double-receive")
module("snippets", "tomcat-war-ssl")
module("snippets", "sockets-client")
module("snippets", "sockets-client-tls")
module("snippets", "sockets-server")
module("snippets", "client-ssl-config")
module("snippets", "client-engine-js")
module("snippets", "client-engine-curl")
module("snippets", "single-page-application")
module("snippets", "ssl-engine-main-redirect")
module("snippets", "ssl-engine-main-hsts")
module("snippets", "json-kotlinx-method-override")
module("snippets", "caching-headers-routes")
module("snippets", "jte")
module("snippets", "custom-plugin-authorization")
module("snippets", "client-configure-request")
if(!System.getProperty("os.name").startsWith("Windows")) {
    module("snippets", "embedded-server-native")
}
if(System.getProperty("os.name") == "Mac OS X") {
    module("snippets", "client-engine-darwin")
}
