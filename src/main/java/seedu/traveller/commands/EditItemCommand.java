
package seedu.traveller.commands;

import seedu.traveller.Day;
import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.Item;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDayCommand.class.getName());
    private final String tripName;
    private final int itemIndex;
    private final String itemName;
    private final String itemTime;
    private final int dayIndex;

    public EditItemCommand(String tripName, int dayIndex, String itemTime, String itemName, int itemIndex) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.itemTime = itemTime;
        this.itemName = itemName;
        this.itemIndex = itemIndex;
        this.dayIndex = dayIndex;
        logger.log(Level.INFO, "Created an edit-item command: \n" + this);
    }

    public String getTripName() {
        return tripName;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemTime() {
        return itemTime;
    }

    @Override
    public String toString() {
        return "Edit-item command:"
                + "\n\ttripName: " + getTripName()
                + "\n\tdayIndex: " + getDayIndex()
                + "\n\titemname: " + getItemIndex()
                + "\n\titemTime: " + getItemTime()
                + "\n\titemName: " + getItemName();
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        Trip trip = tripsList.getTrip(getTripName());
        Day day = trip.getDay(getDayIndex());
        Item newItem = new Item(getItemTime(), getItemName());
        assert Objects.equals(newItem.getItemTime(), getItemTime()) :
                "Item time in created item and command do not match.";
        assert Objects.equals(newItem.getItemName(), getItemName()) :
                "Item name in created item and command do not match.";
        day.editItem(itemIndex, newItem);

        ui.printEditItem(tripName, dayIndex, itemName, itemTime, itemIndex);
    }
}

