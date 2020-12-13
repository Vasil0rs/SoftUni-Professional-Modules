package bakery.repositories.interfaces;

import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DrinkRepositoryImpl implements DrinkRepository<Drink>{
     private List<Drink> drinks;

    public DrinkRepositoryImpl() {
        this.drinks = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return drinks
                .stream()
                .filter(x -> x.getName().equals(drinkName) && x.getBrand().equals(drinkBrand))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Drink> getAll() {
        return Collections.unmodifiableList(this.drinks);
    }

    @Override
    public void add(Drink drink) {
        drinks.add(drink);
    }
}
