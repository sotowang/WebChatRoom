package edu.xjtu.model;

import org.json.JSONObject;

public interface IJsonSerialize {
    public JSONObject toJson();

    public void readFromJson(JSONObject jsonObject);

}
