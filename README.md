# SnappyAndroid

SnappyAndroid is extension of ActiveAndroid ORM. I believe that ActiveAndroid is great tool, but authors decided to take away from us posibility to affect on Id field. That was actually a big deal, because it produced many problems when working with webservice. 

## Getting Started
CHANGES:

1. **Added setter for Id field**

Now you manually set id for object when we retrieved it from webservice or somewhere else. 

2. **Added Expose annotation**

@Expose annotation was added to id field, so now we can use Gson to send and receive Model object. All you need to do is add @Expose annotation for every your field, and use Gson like this:

> Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

If you use Retrofit2 for HTTP requests build it like this:  
>Retrofit.Builder builder = new Retrofit.Builder();  
        builder.baseUrl(baseUrl);  
        builder.client(client);  
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();  
        builder.addConverterFactory(GsonConverterFactory.create(gson));  
        return builder.build();  

3. **Save and delete methods are no longer final**

Now you can override save and delete methods to add some post and pre callbacks. You still need to call super(), because of @CallSuper annotation. This allows you to easily implement cascade save. Just override save method and before calling super(), save your forgein fields.

4. **Added simple Where clause builder**

If you are used to ORMLite, then for sure u won't like ActiveAndroid queryBuilder. I'm going to rebuild it all, but for now I just added easy way to create Where clause, similar to one from ORMlite. 

5. **Default id name**

I found some problem when changing id name for one table, so i set default id name for BaseColumns._ID, which is "_id". It allows you use CursorLoader.

Example:

 >   return new Select().from(ProductGroup.class).where().isNull(ProductGroup.Columns.PARENT_GROUP)  
                        .and().eq(ProductGroup.Columns.VISIBILITY, true)  
                        .or().lt(ProductGroup.Columns.COLOR,15)  
                        .and().notNull(ProductGroup.Columns.NAME)  
                        .orderBy(ProductGroup.Columns.POSITION).execute();  
                        
  ProductGroup.Columns is just inner class of ProductGroup, which contains all names as static Strings.                   

### Prerequisites

You don't have to install ActiveAndroid first, Snappy contains all what it needs.

### Installing

To use SnappyAndroid just download project from GitHub and add it as new module. If u want to keep SnappyAndroid folder in same location as your project, just add those lines to your settings.gradle:

include ':snappyandroid'
project(':snappyandroid').projectDir = new File(settingsDir, 'SnappyAndroid')

and that line to build.gradle:

compile project(':snappyandroid')


## Authors

Michael Pardo | www.michaelpardo.com | www.activeandroid.com - original creator of ActiveAndroid

Rafał Kuźmiński

## License
[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

