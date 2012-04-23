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
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class ZooDocumentWriter {

    XMLStreamWriter xmlWriter;
    String documentFilePath;
    
    public ZooDocumentWriter(String filePath) {
        documentFilePath = filePath;
    }
    
    public void startZooDocument() throws XMLStreamException, FileNotFoundException {
        openWriter();
        startDocument();
        startZooElement();
    }
    
    private void openWriter() throws FileNotFoundException, XMLStreamException {
        FileOutputStream outStream = new FileOutputStream(documentFilePath);
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        xmlWriter = factory.createXMLStreamWriter(outStream, "utf-8");
    }
    
    private void startDocument() throws XMLStreamException {
        xmlWriter.writeStartDocument("utf-8", "1.0");
    }
    
    private void startZooElement() throws XMLStreamException {
        xmlWriter.writeStartElement("zoo");
        xmlWriter.writeDefaultNamespace("ns:uqam:inf4375:exemple:zoo");
    }
    
    public void endZooDocument() throws XMLStreamException {
        endZooElement();
        endDocument();
        closeWriter();
    }
    
    private void endZooElement() throws XMLStreamException {
        xmlWriter.writeEndElement();
    }
    
    private void endDocument() throws XMLStreamException {
        xmlWriter.writeEndDocument();
    }
    
    private void closeWriter() throws XMLStreamException {
        xmlWriter.flush();
        xmlWriter.close();
    }
    
    public void startZoneElement(String zoneName) throws XMLStreamException {
        xmlWriter.writeStartElement("zone");
        xmlWriter.writeAttribute("nom", zoneName);
    }
    
    public void endZoneElement() throws XMLStreamException {
        xmlWriter.writeEndElement();
    }
    
    public void writeAnimalElement(String animalName) throws XMLStreamException {
        xmlWriter.writeStartElement("animal");
        xmlWriter.writeCharacters(animalName);
        xmlWriter.writeEndElement();
    }
    
}
