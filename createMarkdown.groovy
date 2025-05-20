// Copyright (C) 2025  Egon Willighagen
// License: MIT

@Grab(group='io.github.egonw.bacting', module='managers-cdk', version='1.0.5')
@Grab(group='io.github.egonw.bacting', module='managers-rdf', version='1.0.5')
@Grab(group='io.github.egonw.bacting', module='managers-ui', version='1.0.5')
@Grab(group='io.github.egonw.bacting', module='managers-pubchem', version='1.0.5')
@Grab(group='io.github.egonw.bacting', module='managers-inchi', version='1.0.5')

import java.text.SimpleDateFormat;
import java.util.Date;

workspaceRoot = "."
ui = new net.bioclipse.managers.UIManager(workspaceRoot);
cdk = new net.bioclipse.managers.CDKManager(workspaceRoot);
bioclipse = new net.bioclipse.managers.BioclipseManager(workspaceRoot);
inchi = new net.bioclipse.managers.InChIManager(workspaceRoot);
rdf = new net.bioclipse.managers.RDFManager(workspaceRoot);
pubchem = new net.bioclipse.managers.PubChemManager(workspaceRoot);

sparqlEP = "https://compoundcloud.wikibase.cloud/query/sparql"
mdFile = "/example/cmp/paper.md"

qid = args[0]

ui.renewFile(mdFile)
date = new SimpleDateFormat("d MMMMM yyyy").format(new Date());

mappingQuery = """
PREFIX wd: <https://compoundcloud.wikibase.cloud/entity/>
PREFIX wdt: <https://compoundcloud.wikibase.cloud/prop/direct/>

PREFIX target: <https://compoundcloud.wikibase.cloud/entity/Q1>

SELECT ?cmp ?cmpLabel ?mass ?chemformula ?pubchem ?smiles ?inchi ?inchikey
WHERE {
  VALUES ?cmp { target: }
  OPTIONAL { ?cmp wdt:P2 ?mass }
  OPTIONAL { ?cmp wdt:P3 ?chemformula }
  OPTIONAL { ?cmp wdt:P13 ?pubchem }
  OPTIONAL { ?cmp wdt:P7 ?smiles }
  OPTIONAL { ?cmp wdt:P9 ?inchi }
  OPTIONAL { ?cmp wdt:P10 ?inchikey }
  SERVICE wikibase:label { bd:serviceParam wikibase:language "[AUTO_LANGUAGE],en". }
}
"""
rawResults = bioclipse.sparqlRemote(sparqlEP, mappingQuery)
results = rdf.processSPARQLXML(rawResults, mappingQuery)

cmpResults = results.getRow(1)
cmpName = cmpResults[1]
cmpMass = cmpResults[2]
cmpFormula = cmpResults[3]
cmpPubChem = cmpResults[4]
cmpSMILES = cmpResults[5]
cmpInChI = cmpResults[6]
cmpInChIKey = cmpResults[7]

ui.append(mdFile, """---
title: '${cmpName}'
title_short: '${cmpName}'
authors:
  - name: 
  - name: 
date: ${date}
cito-bibliography: paper.bib
authors_short: VHP4Safety
---
""")

ui.append(mdFile, """
The compound has the SMILES
`${cmpSMILES}`
and
`${cmpInChI}`.
""")
