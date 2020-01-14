package org.musical_pursuit.services;

import org.musical_pursuit.services.src.FactoryPackage.IPlayCardFactory;
import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;

import java.util.concurrent.Callable;

public class MyThread implements Callable<IPlayCard> {

    IPlayCardFactory factory;

    public MyThread(IPlayCardFactory factory) {
        this.factory = factory;
    }

    @Override
    public IPlayCard call() throws Exception {
        return factory.CreatePlayCard();
    }
}
