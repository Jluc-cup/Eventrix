package com.eventrix.model.localobj;

import com.eventrix.service.strategy.StrategyContextInterface;

public record CommandDeleteObj(int id) implements StrategyContextInterface {

}
