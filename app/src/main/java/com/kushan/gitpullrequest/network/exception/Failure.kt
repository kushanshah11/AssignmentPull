package  com.kushan.gitpullrequest.network.exception

sealed class Failure(override var message : String = "", var retry : () -> Unit ={}) : Exception() {

    class NetworkConnection(override var message : String = ""): Failure()
    class ServerError(override var message : String = "", val errorCode : Int = 500): Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure( override  var message : String = ""): Failure()

}
