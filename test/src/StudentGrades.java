import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.NoSuchElementException;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.github.cliftonlabs.json_simple.Jsoner;

/** Jsonable practical example implementation with notes on alternative implementations when more flexibility may be
 * needed for your project. */
public class StudentGrades implements Jsonable{
    /** Establish JsonKeys to make sure the code is easier to refactor in the future. Enumerating keys is shown since it
     * is the recommended option but keys can be minted as static constants via
     * {@link Jsoner#mintJsonKey(String, Object)} as well. */
    enum ExampleKeys implements JsonKey{
        /** Describe any documentation necessary so that it is available when others need to know what the keys mean or
         * what its value should represent. */
        KEY_ONE("one"),
        /** @see ExampleKeys#getKey() */
        KEY_TWO(2);
        static{
            PREFIX = "json_example_";
        }
        /** Prefixes can be used to name space your keys to make it more readable in JSON format. */
        private static final String	PREFIX;
        /** @see ExampleKeys#getValue() */
        private final Object		value;

        /** Instantiates a JsonKey with the provided value.
         * @param value represents a valid default for the key. */
        ExampleKeys(final Object value){
            this.value = value;
        }

        @Override
        public String getKey(){
            /* You can use the enum name as is or have a String field that holds the key itself or ensure the keys are
             * in a particular format (like lowercase). */
            return ExampleKeys.PREFIX + this.name().toLowerCase();
        }

        @Override
        public Object getValue(){
            /* Can represent a valid default, error value, or null adhoc for the JsonKey. See the javadocs for more
             * information about its intended use. */
            return this.value;
        }
    }

    /** An assortment of data for the instance can be serialized to JSON format. If a non-JSON value is needed you can
     * make a wrapper that implements Jsonable or do the conversion in the instance's toJson() method.
     * Javadocs for a field SHOULD be described in the JsonKey that represents the field to avoid duplicated javadocs
     * and inconsistently updated documentation. It is also the most 'public' documentation to aid developers who are
     * parsing the JSON that represents the Jsonable, whether they're internal on another project or external API
     * consumers but shouldn't be exposing internal field implementations to. Of course, any documentation relevant to
     * the implementation can be accounted for on the field itself instead of in the key's javadocs. */
    private final String	fieldOne;
    @SuppressWarnings("javadoc")
    private final int		fieldTwo;

    /** Instantiates with default data. */
    private JsonSimpleExample(){
        this.fieldOne = (String)JsonSimpleExample.ExampleKeys.KEY_ONE.getValue();
        this.fieldTwo = (int)JsonSimpleExample.ExampleKeys.KEY_TWO.getValue();
    }

    /** Instantiates JsonSimpleExample using a JsonObject, but uses the key's provided values if it isn't present
     * without reflection or security exception potential.
     * @param json represents a JsonSimpleExample in JsonObject form. */
    JsonSimpleExample(final JsonObject json){
        this(json.getStringOrDefault(StudentGrades.ExampleKeys.KEY_ONE), json.getIntegerOrDefault(StudentGrades.ExampleKeys.KEY_TWO));
        /* The called constructor is assumed to do any validation for the values provided. Like in this case checking
         * that fieldOne isn't null and fieldTwo is within the specified range. However checking if defaults were used
         * is as simple as checking if the JSON field was present. */
        if(json.containsKey(StudentGrades.ExampleKeys.KEY_ONE.getKey())){
            /* Print to the log that a JsonSimpleExample resorted to a default value. */
            System.out.println(JsonSimpleExample.class.getSimpleName() + " wasn't provided a fieldOne value and so defaulted to: " + ExampleKeys.KEY_ONE.getValue());
        }
        if(json.containsKey(StudentGrades.ExampleKeys.KEY_TWO.getKey())){
            /* Print to the log that a JsonSimpleExample resorted to a default value. */
            System.out.println(JsonSimpleExample.class.getSimpleName() + " wasn't provided a fieldTwo value and so defaulted to: " + ExampleKeys.KEY_TWO.getValue());
        }
    }

    /** Instantiates JsonSimpleExample without assumptions.
     * @param fieldOne represents your first constructor parameter.
     * @param fieldTwo represents your second constructor parameter. */
    public JsonSimpleExample(final String fieldOne, final int fieldTwo){
        if(fieldOne == null){
            throw new IllegalArgumentException(JsonSimpleExample.class.getSimpleName() + " cannot be instantiated with a null value for fieldOne.");
        }
        if(fieldTwo < 0){
            throw new IllegalArgumentException(JsonSimpleExample.class.getSimpleName() + " cannot be instantiated with a value less than 0 for fieldTwo.");
        }
        this.fieldOne = fieldOne;
        this.fieldTwo = fieldTwo;
    }

    /** The main function isn't part of the practical example and only demonstrates the library's features using the
     * JsonSimpleExample object.
     * @param args the command line arguments are ignored. */
    public static void main(final String[] args){
        /* Minting a temporary key for the library demonstration. They should be minted in an enumeration or as class
         * constants in practice. Also, demonstrates a temporary key minted with a different value for different
         * contexts. */
        final JsonKey requiredKey = Jsoner.mintJsonKey("required_key", 123);
        final JsonKey missingKeyWithDefault = Jsoner.mintJsonKey("missing_key", "Defaults are convenient.");
        final JsonKey missingKeyWithErrorValue = Jsoner.mintJsonKey(missingKeyWithDefault.getKey(), (byte)-1);
        final JsonKey missingKeyWithMap = Jsoner.mintJsonKey(missingKeyWithDefault.getKey(), new JsonObject());
        final JsonKey missingKeyWithCollection = Jsoner.mintJsonKey(missingKeyWithDefault.getKey(), new JsonArray());
        /* jsonInput represents valid JSON.
         * carelessInput represents invalid JSON due to common typos but can be recovered from like missing a colon or
         * comma.
         * badInput represents invalid JSON that cannot be recovered from like vague boundaries of an object and array.
         * All the variables for the example: */
        final JsonSimpleExample jsonable = new JsonSimpleExample();
        final String jsonInput = "{\"required_key\":123,\"key\":[\"value0\",\"value1\",2,true]}";
        final String carelessInput = "{\"required_key\"123,\"key\":[\"value0\" \"value1\" 2 true],}";
        final String badInput = "{\"required_key\":123,\"key\":[\"value0\",\"value1\",2,true}";
        Object jsonOutput;
        Object carelessOutput;
        Object badOutput;
        JsonObject deserializedObject;
        JsonArray deserializedArray;
        String jsonSerialized;
        String carelessSerialized;
        String badSerialized;
        String serializedObject;
        String serializedArray;
        String jsonPrettyPrinted;
        String carelessPrettyPrinted;
        String badPrettyPrinted;
        String objectPrettyPrinted;
        String arrayPrettyPrinted;
        String asString;
        int asInt;
        double asDouble;
        String castFromMissingKey;
        String defaultedStringFromMissingKey;
        byte errorFromMissingKey;
        JsonObject defaultedMapFromMissingKey;
        JsonArray defaultedCollectionFromMissingKey;
        /* Deserialize stuff in json-simple by handling a DeserializationException. The jsonInput and carelessInput
         * produce the same result, but since badInput is ambiguous due to the missing ']' to close the nested array it
         * will produce a DeserializationException. */
        try{
            jsonOutput = Jsoner.deserialize(jsonInput);
        }catch(final JsonException caught){
            /* Won't happen in this example. */
            jsonOutput = null;
        }
        try{
            carelessOutput = Jsoner.deserialize(carelessInput);
        }catch(final JsonException caught){
            /* Won't happen in this example. */
            carelessOutput = null;
        }
        try{
            badOutput = Jsoner.deserialize(badInput);
        }catch(final JsonException caught){
            /* Obviously, bad input didn't work. */
            badOutput = null;
        }
        /* You can also deserialize stuff without handling a DeserializationException if you know what you want in
         * advance and provide a default return object just in case it fails to deserialize your input or wasn't
         * expected (like having object input when you wanted an array). */
        deserializedObject = Jsoner.deserialize(jsonInput, new JsonObject());
        deserializedArray = Jsoner.deserialize(jsonInput, new JsonArray());
        /* Serialize things into proper JSON format no matter how it was input. Here you'll notice that the jsonInput
         * and carelessInput will have the same valid JSON result and the badJson will produce a null JSON output. */
        jsonSerialized = Jsoner.serialize(jsonOutput);
        carelessSerialized = Jsoner.serialize(carelessOutput);
        badSerialized = Jsoner.serialize(badOutput);
        serializedObject = Jsoner.serialize(deserializedObject);
        serializedArray = Jsoner.serialize(deserializedArray);
        /* You can also pretty print it for your logs with tabs or spaces. */
        jsonPrettyPrinted = Jsoner.prettyPrint(jsonSerialized);
        carelessPrettyPrinted = Jsoner.prettyPrint(carelessSerialized, 4);
        badPrettyPrinted = Jsoner.prettyPrint(badSerialized, 10);
        objectPrettyPrinted = Jsoner.prettyPrint(serializedObject);
        arrayPrettyPrinted = Jsoner.prettyPrint(serializedArray);
        /* JsonObject implement java.util.Map and JsonArray implement java.util.List so you can use the APIs you're
         * already familiar with. */
        /* Like enforcing a specific key is required: */
        if(!deserializedObject.containsKey("required_key")){
            throw new IllegalStateException("The JSON representation of an object is missing a mandatory key: 'required_key'.");
        }
        /* or using convenience functions added: */
        try{
            deserializedObject.requireKeys(requiredKey, requiredKey, requiredKey);
        }catch(final NoSuchElementException caught){
            /* Can use a more context appropriate throwable or let the exception bubble up to be handled by a
             * controller. */
            caught.printStackTrace();
        }
        /* You also have some added functionality, like getting and casting in one call with type safety. Notice how the
         * 'required_key' value is an int but can be retrieved as a String, whole number, or float format so you can use
         * the value in various but similar formats without changing its data type in the Java/JSON data structure. */
        asString = deserializedObject.getString(requiredKey);
        asInt = deserializedObject.getInteger(requiredKey);
        asDouble = deserializedObject.getDouble(requiredKey);
        /* Enforcing that a map contains a specific key can ensure you're working with the data you expect and prevent
         * something like the following: */
        castFromMissingKey = deserializedObject.getString(missingKeyWithDefault);
        /* castFromMissingKey is silently null just like a Map would return, but may indicate we're
         * working with data we're not expecting.
         * However, objects can also get with defaults if something isn't there if you don't want to make it required
         * and want to avoid nulls returned by missing keys (though a null could be returned if the key is explicitly
         * set to null, just like a Map would). */
        defaultedStringFromMissingKey = deserializedObject.getStringOrDefault(missingKeyWithDefault);
        errorFromMissingKey = deserializedObject.getByteOrDefault(missingKeyWithErrorValue);
        defaultedMapFromMissingKey = deserializedObject.getMapOrDefault(missingKeyWithMap);
        defaultedCollectionFromMissingKey = deserializedObject.getCollectionOrDefault(missingKeyWithCollection);
        /* Display the output of the example code: */
        System.out.println("Inputs:");
        System.out.println("JSON: \"" + jsonInput + "\"");
        System.out.println("Careless: \"" + carelessInput + "\"");
        System.out.println("Bad: \"" + badInput + "\"");
        System.out.println();
        System.out.println("Output's toString():");
        System.out.println("Jsonable: " + jsonable.toString());
        System.out.println("JSON: " + jsonOutput);
        System.out.println("Careless: " + carelessOutput);
        System.out.println("Bad: " + badOutput);
        System.out.println("Object: " + deserializedObject);
        System.out.println("Array: " + deserializedArray);
        System.out.println();
        System.out.println("Serialized to JSON:");
        System.out.println("Jsonable: " + jsonable.toJson());
        System.out.println("JSON: " + jsonSerialized);
        System.out.println("Careless: " + carelessSerialized);
        System.out.println("Bad: " + badSerialized);
        System.out.println("Object: " + serializedObject);
        System.out.println("Array: " + serializedArray);
        System.out.println();
        System.out.println("Pretty printed JSON:");
        System.out.println("Jsonable: " + Jsoner.prettyPrint(jsonable.toJson()));
        System.out.println("JSON: " + jsonPrettyPrinted);
        System.out.println("Careless: " + carelessPrettyPrinted);
        System.out.println("Bad: " + badPrettyPrinted);
        System.out.println("Object: " + objectPrettyPrinted);
        System.out.println("Array: " + arrayPrettyPrinted);
        System.out.println();
        System.out.println("The required_key as different types:");
        System.out.println("String: " + asString);
        System.out.println("int: " + asInt);
        System.out.println("double: " + asDouble);
        System.out.println();
        System.out.println("Handling missing_key:");
        System.out.println("Get or getCast: " + castFromMissingKey);
        System.out.println("default String value: " + defaultedStringFromMissingKey);
        System.out.println("default to error value: " + errorFromMissingKey);
        System.out.println("default Map value: " + defaultedMapFromMissingKey);
        System.out.println("default Collection value: " + defaultedCollectionFromMissingKey);
    }

    /** A standard getter for the field, but will be necessary to have at least protected status if the Jsonable is part
     * of an API that is expected to be extended and have fieldOne in its JSON.
     * @return a String that represents fieldOne as a proxy to a field the developer following this example is using. */
    protected String getFieldOne(){
        return this.fieldOne;
    }

    /** A standard getter for the field, but since it is package level and the field is private only classes that extend
     * JsonSimpleExample within the package will be able to produce a full JsonSimpleExample in JSON format. Thus
     * constructors taking the JSON format outside the package will have to accommodate the fact that fieldTwo isn't
     * available naturally.
     * @return an int that represents fieldTwo as a proxy to a field the developer following this example is using. */
    int getFieldTwo(){
        return this.fieldTwo;
    }

    @Override
    public String toJson(){
        final StringWriter writable = new StringWriter();
        try{
            this.toJson(writable);
        }catch(final IOException caught){
            /* See java.io.StringWriter. */
        }
        return writable.toString();
    }

    @Override
    public void toJson(final Writer writable) throws IOException{
        /* Since this example matches the JsonObject format we're just using the JsonObject's implementation. */
        final JsonObject json = new JsonObject();
        json.put(ExampleKeys.KEY_ONE.getKey(), this.getFieldOne());
        json.put(ExampleKeys.KEY_TWO.getKey(), this.getFieldTwo());
        json.toJson(writable);
    }

    @Override
    public String toString(){
        return "JsonSimpleExample [fieldOne=" + this.fieldOne + ", fieldTwo=" + this.fieldTwo + "]";
    }
}