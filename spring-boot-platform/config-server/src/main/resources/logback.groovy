import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.status.OnConsoleStatusListener

// For syntax, see http://logback.qos.ch/manual/groovy.html
// Logging detail levels: TRACE > DEBUG > INFO > WARN > ERROR
def logPattern = "%d{yyyy-MM-dd_HH:mm:ss.SSS} [%thread] [%X{X-B3-TraceId:-}, %X{X-B3-SpanId:-}, %X{X-Span-Export:-} ] %-5level %logger{66} - %msg%n"
def logPath = "./logs/application.log"

jmxConfigurator()
displayStatusOnConsole()
setupAppenders(logPath, logPattern)
setupLoggers()

def displayStatusOnConsole() {
    statusListener OnConsoleStatusListener
}

def setupAppenders(logPath, logPattern) {

    appender("FILE", RollingFileAppender) {
        file = "${logPath}"
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${logPath}.%d{yyyy-MM-dd}.%i.zip"
            timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
                maxFileSize = "200MB"
            }
            maxHistory = 3
        }
        encoder(PatternLayoutEncoder) {
            pattern = logPattern
        }
    }

    appender("CONSOLE", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = logPattern
        }
    }
}

def setupLoggers() {
    logger("com.valchkou", INFO)
    logger("org.springframework", DEBUG)
    root(DEBUG, ["FILE", "CONSOLE"])
}
