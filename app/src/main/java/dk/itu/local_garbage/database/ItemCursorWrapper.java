package dk.itu.local_garbage.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import dk.itu.local_garbage.Item;
import dk.itu.local_garbage.database.ItemsDbSchema.ItemTable;

public class ItemCursorWrapper extends CursorWrapper{
    public ItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Item getItem() {
        String what = getString(getColumnIndex(ItemTable.Cols.WHAT));
        String place = getString(getColumnIndex(ItemTable.Cols.PLACE));
        return new Item(what, place);
    }
}
