package com.currency.converter.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    List<Item> items = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Menu() {

    }

    public Menu addItem(Item item) {
        this.items.add(item);
        return this;
    }

    public List<Item> getItems() {
        items.sort((item1, item2) -> item1.compareTo(item2));
        return items;
    }

    public void displayMenu() {
        System.out.println("------------------------------------");
        System.out.println("Menu do Conversor\n");
        for (Item item : this.getItems()) {
            System.out.println(item);
        }
        System.out.println("------------------------------------");
    }

    public int getUserInput() {
        Integer input = null;
        Optional<Item> item = Optional.empty();
    
        while (!item.isPresent()) {
            try{
                System.out.print("\nDigite uma opção (-1 para sair): ");
                input = Integer.parseInt(this.scanner.next());
                if(input.equals((-1))){
                    break;
                }else if(input < 0){
                    this.errorMsg();
                    continue;
                }
            }catch(NumberFormatException e){
                this.errorMsg();
                continue;
            }
            item = getItemById(input);
    
            if (!item.isPresent()) {
                System.out.println("ID inválido. Por favor, tente novamente.");
            }else{
                item.get().run();
            }
        }
        return input;
    }

    public void build() {
        int input = 0;
        while(input != -1){
            this.displayMenu();
            input = this.getUserInput();
        }
    }

    private Optional<Item> getItemById(Integer id) {
        return this.getItems()
                .stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst();
    }

    private void errorMsg(){
        System.out.println("Opção inválida.");
    }
}
