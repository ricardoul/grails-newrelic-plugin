package au.com.sensis.newrelic

import grails.test.mixin.*
import org.junit.*
import grails.util.Environment

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(NewrelicTagLib)
class NewrelicTagLibTests {

    def NewrelicTagLib tagLib

    @Before
    void setUp() {
        tagLib = applicationContext.getBean(NewrelicTagLib)
        tagLib.grailsApplication =  [ config: [:] ]
    }

    void testShouldBeEnabledForProductionByDefault() {
        setEnvironment(Environment.PRODUCTION)

        assert tagLib.enabled == true
    }

    void testShouldBeDisabledForNonProductionByDefault() {
        setEnvironment(Environment.CUSTOM)

        assert tagLib.enabled == false
    }

    void testShouldBeEnabledWhenConfigEnablesNewRelic() {
        enableNewRelic(true)

        assert tagLib.enabled == true
    }

    void testShouldBeDisabledForProductionWhenConfigDisablesNewRelic() {
        setEnvironment(Environment.PRODUCTION)
        enableNewRelic(false)

        assert tagLib.enabled == false
    }

    private setEnvironment(environment) {
        Environment.metaClass.static.getCurrent = { ->
            return environment
        }
    }

    private enableNewRelic(boolean value){
        tagLib.grailsApplication.config = [
                newrelic: [
                        enabled: value
                ]
        ]
    }
}
