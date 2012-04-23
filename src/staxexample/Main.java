/* Copyright 2011 Jacques Berger

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
package staxexample;

public class Main {
    
    public static void main(String[] args) throws Exception {
        displayAnimalNames();
        writeZooDocument();
    }
    
    private static void displayAnimalNames() throws Exception {
        ZooDocumentReader reader = new ZooDocumentReader("xml/zoo.xml");
        for (String animalName : reader.getAnimalNameList())
            System.out.println(animalName);
    }
    
    private static void writeZooDocument() throws Exception {
        ZooDocumentWriter writer = new ZooDocumentWriter("xml/zoo_new_stax.xml");
        writer.startZooDocument();
        writer.startZoneElement("domestique");
        writer.writeAnimalElement("chien");
        writer.writeAnimalElement("chat");
        writer.writeAnimalElement("perroquet");
        writer.endZoneElement();
        writer.endZooDocument();
    }
}
