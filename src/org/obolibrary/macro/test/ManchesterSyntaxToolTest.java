package org.obolibrary.macro.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.obolibrary.macro.ManchesterSyntaxTool;
import org.obolibrary.obo2owl.Owl2Obo;
import org.obolibrary.obo2owl.test.OboFormatTestBasics;
import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 * Tests for {@link ManchesterSyntaxTool}.
 */
public class ManchesterSyntaxToolTest extends OboFormatTestBasics {

	private OWLOntology owlOntology = null;
	private ManchesterSyntaxTool parser = null;
	
	@Test
	public void testParseManchesterIds() throws IOException, OWLOntologyCreationException, ParserException {
		setup();
		OWLClassExpression expression = parser.parseManchesterExpression("GO_0018901 AND BFO:0000050 some GO_0055124");
		checkIntersection(expression, "GO:0018901", "BFO:0000050", "GO:0055124");
	}
	
	@Test
	public void testParseManchesterNames() throws IOException, OWLOntologyCreationException, ParserException {
		setup();
		OWLClassExpression expression = parser.parseManchesterExpression("'2,4-dichlorophenoxyacetic acid metabolic process' AND 'part_of' some 'premature neural plate formation'");
		checkIntersection(expression, "GO:0018901", "BFO:0000050", "GO:0055124");
	}

	private synchronized void setup() throws OWLOntologyCreationException, IOException {
		if (owlOntology == null) {
			owlOntology = convert(parseOBOFile("simplego.obo"));
			parser = new ManchesterSyntaxTool(owlOntology);
		}
	}

	private void checkIntersection(OWLClassExpression expression, String genus, String relId, String differentia) {
		OWLObjectIntersectionOf intersection = (OWLObjectIntersectionOf) expression;
		List<OWLClassExpression> list = intersection.getOperandsAsList();
		OWLClass cls = (OWLClass) list.get(0);
		assertEquals(genus, Owl2Obo.getIdentifier(cls.getIRI()));
		OWLClassExpression rhs = list.get(1);
		OWLClass cls2 = rhs.getClassesInSignature().iterator().next();
		assertEquals(differentia, Owl2Obo.getIdentifier(cls2.getIRI()));
		OWLObjectProperty property = rhs.getObjectPropertiesInSignature().iterator().next();
		assertEquals(relId, Owl2Obo.getIdentifier(property.getIRI()));
	}

}
