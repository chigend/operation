package com.cookabuy.util.selector;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * 抽象分拣器
 *
 * @param <CONDITION> 分拣条件
 * @param <ENTITY>    待分拣物品
 * @author Administrator
 */
public abstract class Selector<CONDITION, ENTITY> {

    // 存货柜
    private Map<CONDITION, Cell> cabinet = new HashMap<>();

    protected abstract CONDITION selectCondition(ENTITY entity);

    /**
     * 分拣动作 可以一个一个手动分拣，也可以接受Collection来分拣
     * @param entity
     */
    public void select(ENTITY entity) {
        CONDITION condition = selectCondition(entity);
        if (cabinet.containsKey(condition)) {
            cabinet.get(condition).putItem(entity);
        } else {
            // 没有这个格子，构造一个
            Cell wrapper = new Cell(condition);
            wrapper.putItem(entity);
            cabinet.put(condition, wrapper);
        }
    }

    public void select(Collection<ENTITY> unsortedItems){
        unsortedItems.stream().forEach(this::select);
    }

    /**
     * @return
     */
    public Collection<Cell> getSelectResult() {
        return cabinet.values();
    }

    /**
     * 分拣后每个CONDITION下都是一个Cell,该类表示经分拣后每个条件下的包含的ENTITY和当前的筛选条件
     */
    @Setter
    @Getter
    private class Cell {
        private Set<ENTITY> childs;

        private CONDITION condition;

        public Cell(CONDITION condition) {
            this.condition = condition;
            childs = new HashSet<>();
        }

        public void putItem(ENTITY entity) {
            childs.add(entity);
        }
    }

}
