package au.com.sensis.newrelic

import grails.util.Environment
import com.newrelic.api.agent.NewRelic

class NewrelicTagLib {
    static namespace = "newrelic"

    def grailsApplication

    def browserTimingHeader = {
        if(enabled) {
            out << NewRelic.browserTimingHeader
        }
    }

    def browserTimingFooter = {
        if(enabled) {
            out << NewRelic.browserTimingFooter
        }
    }

    boolean isEnabled() {
        // default enabled for PROD
        boolean configEnabled = (Environment.current == Environment.PRODUCTION)

        // if config specified, use that instead
        if (grailsApplication.config.newrelic) {
            configEnabled = grailsApplication.config.newrelic.enabled
        }

        return configEnabled
    }
}
