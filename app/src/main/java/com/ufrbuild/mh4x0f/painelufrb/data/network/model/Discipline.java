/*
    This file is part of the Painel de Aulas UFRB Open Source Project.
    Painel de Aulas UFRB is licensed under the Apache 2.0.

    Copyright 2019 UFRBuild - Marcos Bomfim

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

package com.ufrbuild.mh4x0f.painelufrb.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discipline implements Parcelable{

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("room_name")
    private String room_name;

    @Expose
    @SerializedName("start_time")
    private long start_time;

    @Expose
    @SerializedName("duration")
    private long duration;

    @Expose
    @SerializedName("status")
    private int status;

    protected Discipline(Parcel in) {
        name = in.readString();
        description = in.readString();
        room_name = in.readString();
        duration = in.readLong();
        start_time = in.readLong();
        status = in.readInt();
    }

    public static final Creator<Discipline> CREATOR = new Creator<Discipline>() {
        @Override
        public Discipline createFromParcel(Parcel in) {
            return new Discipline(in);
        }

        @Override
        public Discipline[] newArray(int size) {
            return new Discipline[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    public int getStatus() {
        return status;
    }
    public long getDuration() {
        return duration;
    }
    public long getStart_time() {
        return start_time;
    }
    public String getRoom_name() {
        return room_name;
    }

    public void setDescription(String ds) {
        this.description = ds;
    }


    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(status);
        parcel.writeLong(start_time);
        parcel.writeLong(duration);
        parcel.writeString(room_name);
        parcel.writeString(description);
        parcel.writeString(name);
    }
}