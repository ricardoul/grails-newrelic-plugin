package au.com.sensis.newrelic

class NewRelicFilters {

    def newRelicService

    def filters = {
        all(uri:"/**") {
            before = {
                if(controllerName) {
                    newRelicService.setTransactionName(null, "/${controllerName}"+(actionName ? "/${actionName}" : "/"))
                } else {
                    newRelicService.setTransactionName(null, request.getServletPath())
                }

                return true
            }
        }
    }
}
