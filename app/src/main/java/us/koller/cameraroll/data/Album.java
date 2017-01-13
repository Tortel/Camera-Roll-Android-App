package us.koller.cameraroll.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Album implements Parcelable {

    private ArrayList<AlbumItem> albumItems;

    private String name;

    public boolean hiddenAlbum = false;

    public Album() {
        albumItems = new ArrayList<>();
    }

    public Album setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArrayList<AlbumItem> getAlbumItems() {
        return albumItems;
    }

    private Album(Parcel parcel) {
        name = parcel.readString();
        hiddenAlbum = Boolean.parseBoolean(parcel.readString());
        albumItems = new ArrayList<>();
        albumItems = parcel.createTypedArrayList(AlbumItem.CREATOR);
    }

    @Override
    public String toString() {
        return getName() + ": " + getAlbumItems().toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(String.valueOf(hiddenAlbum));
        AlbumItem[] albumItems = new AlbumItem[this.albumItems.size()];
        for (int k = 0; k < albumItems.length; k++) {
            albumItems[k] = this.albumItems.get(k);
        }
        parcel.writeTypedArray(albumItems, 0);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Album createFromParcel(Parcel parcel) {
            return new Album(parcel);
        }

        @Override
        public Album[] newArray(int i) {
            return new Album[i];
        }
    };
}