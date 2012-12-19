package au.com.sensis.newrelic

class NewRelicFilters {

    def newRelicService

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if(controllerName) {
                    newRelicService.setTransactionName(null, "/${controllerName}"+(actionName ? "/${actionName}" : "/"))
                }

                return true
            }
        }
    }
}
