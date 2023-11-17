package com.orhanobut.dialog.observable

import com.ved.framework.bus.event.SingleLiveEvent

class UIChangeObservable<T> {

    var pSwitchEvent = SingleLiveEvent<T>()
}