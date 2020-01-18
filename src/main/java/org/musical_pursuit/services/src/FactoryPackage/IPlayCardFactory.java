package org.musical_pursuit.services.src.FactoryPackage;

import org.musical_pursuit.services.src.PlayCardPackage.IPlayCard;

// An abstract factory for creating playcards.
public interface IPlayCardFactory {

    public IPlayCard CreatePlayCard();
}
