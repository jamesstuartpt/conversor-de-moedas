package com.currency.converter.menu;

import com.currency.converter.menu.interfaces.ItemMenu;

public abstract class Item implements ItemMenu, Comparable<Item>{

    private Integer id;
    private String name;


    public Item(){

    }

    public Item(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int compareTo(Item oItem){
        if(this.equals(oItem)){
            return 0;
        }
        return this.getId().compareTo(oItem.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", this.getId(), this.getName());
    }
}
