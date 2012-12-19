package au.com.sensis.newrelic

import grails.util.Environment
import spock.lang.Specification
import org.codehaus.groovy.grails.commons.GrailsApplication

class NewRelicServiceSpec extends Specification {

    def NewRelicService service = new NewRelicService()

    def setup() {
        service.grailsApplication = [ config: [:] ]
    }

    def "should be enabled for PRODUCTION by default"() {
        when:
            setEnvironment(Environment.PRODUCTION)

        then:
            assert service.enabled == true
    }

    def "should be disabled for NON-PRODUCTION by default" () {
        when:
            setEnvironment(Environment.CUSTOM)

        then:
            assert service.enabled == false
    }

    def "should be enabled when config enables NewRelic" () {
        when:
            enableNewRelic(true)

        then:
            assert service.enabled == true
    }

    def "should be disabled for PRODUCTION when config disables NewRelic" () {
        when:
            setEnvironment(Environment.PRODUCTION)
            enableNewRelic(false)

        then:
            assert service.enabled == false
    }

    private setEnvironment(environment) {
        Environment.metaClass.static.getCurrent = { ->
            return environment
        }
    }

    private enableNewRelic(boolean value){
        service.grailsApplication.config = [
            newrelic: [
                enabled: value
            ]
        ]
    }
}
