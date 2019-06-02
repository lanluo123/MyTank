package com.TA.strategy;

import com.TA.GameModel;
import com.TA.Tank;

import java.io.Serializable;

public interface Fire extends Serializable {
    public void fire(Tank tank);
}
