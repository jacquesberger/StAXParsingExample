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
package org.jberger.staxparsingexample.stax;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ZooDocumentReader {

    XMLEventReader eventReader;
    String documentFilePath;
    
    public ZooDocumentReader(String filePath) {
        documentFilePath = filePath;
    }
    
    public List<String> getAnimalNameList() throws XMLStreamException, FileNotFoundException {
        openZooDocument();
        List<String> list = buildAnimalNameList();
        closeZooDocument();
        return list;
    }
    
    private void openZooDocument() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileReader inputFile = new FileReader(documentFilePath);
        eventReader = factory.createXMLEventReader(inputFile);
    }
    
    private void closeZooDocument() throws XMLStreamException {
        eventReader.close();
    }
    
    private List<String> buildAnimalNameList() throws XMLStreamException {
        List<String> list = new ArrayList<String>();
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (isAnimalStartElement(event))
                list.add(getTextContentFromStartElement());
        }
        return list;
    }
    
    private boolean isAnimalStartElement(XMLEvent event) {
        if (event.isStartElement()) {
            String localName = getLocalNameOfStartElement(event.asStartElement());
            if (localName.equals("animal")) {
                return true;
            }
        }
        return false;
    }
    
    private String getLocalNameOfStartElement(StartElement startElement) {
        QName elementName = startElement.getName();
        return elementName.getLocalPart();
    }
    
    private String getTextContentFromStartElement() throws XMLStreamException {
        XMLEvent content = eventReader.nextEvent();
        Characters charContent = content.asCharacters();
        return charContent.getData();
    }
}
