package proj.zav.jra.models._json;

import org.json.JSONArray;
import org.json.JSONObject;
import proj.zav.jra.models.Kind;
import proj.zav.jra.models.Thing;
import proj.zav.jra.models.Trophy;
import proj.zav.jra.models.TrophyList;

public class JSONTrophyList extends JSONTrophyListTOP{
    public static final String KEY = "trophies";

    public static TrophyList fromThing(String source){
        Thing thing = JSONThing.fromJson(source);

        assert thing.getKind() == Kind.TrophyList;

        return fromJson(new TrophyList(), thing.getData().toString());
    }

    @Override
    protected void $fromData(JSONObject source, TrophyList target){
        JSONArray values = source.optJSONArray(KEY);

        if(values != null) {
            for (int i = 0; i < values.length(); ++i)
                target.addData(JSONTrophy.fromThing(values.getJSONObject(i)));
        }
    }

    @Override
    protected void $toData(TrophyList source, JSONObject target){
        JSONArray values = new JSONArray();

        if(!source.isEmptyData()) {
            for (Trophy data : source.getData())
                values.put(JSONTrophy.toJson(data, new JSONObject()));
            target.put(KEY, values);
        }
    }
}
