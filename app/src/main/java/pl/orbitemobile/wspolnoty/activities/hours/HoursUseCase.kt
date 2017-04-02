package pl.orbitemobile.wspolnoty.activities.hours

import io.reactivex.Single
import pl.orbitemobile.kotlinrx.fromIoToMainThread
import pl.orbitemobile.wspolnoty.data.RemoteRepositoryImpl

class HoursUseCase : HoursContract.UseCase {
    override fun getRemoteHours(): Single<String> =
            RemoteRepositoryImpl.instance.getHours().fromIoToMainThread()


}