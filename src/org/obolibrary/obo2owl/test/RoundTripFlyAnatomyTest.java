package org.obolibrary.obo2owl.test;

import java.io.IOException;
import java.util.Collection;

import org.obolibrary.obo2owl.Obo2Owl;
import org.obolibrary.oboformat.model.Frame;
import org.obolibrary.oboformat.model.OBODoc;
import org.obolibrary.oboformat.model.Xref;
import org.obolibrary.oboformat.parser.OBOFormatParser;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import junit.framework.TestCase;

public class RoundTripFlyAnatomyTest extends RoundTripTest {

	static OWLOntologyManager manager;
	static OWLDataFactory df;
	static OWLOntology ontology;

	public static void testRoundTrip() throws IOException, OWLOntologyCreationException, OWLOntologyStorageException {
		roundTripOBOURL("http://obo.cvs.sourceforge.net/*checkout*/obo/obo/ontology/anatomy/gross_anatomy/animal_gross_anatomy/fly/fly_anatomy_XP.obo", true);
		
	}
	
	
}
