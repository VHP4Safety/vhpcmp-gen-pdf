---
title: 'BioHackrXiv template this is an example of a (too) long title mpla mpla mpla mpla mpla mpla mpla mpla mpla mpla mpla c wjfc wjknwjek nwjkwen jk'
title_short: 'Logic Programming for the Biomedical Sciences'
tags:
  - logic programming
authors:
  - name: Chris Mungall
    orcid: 0000-0002-6601-2165
    affiliation: 1
  - name: Hirokazu Chiba
    affiliation: 2
    orcid: 0000-0003-4062-8903
  - name: Shuichi Kawashima
    affiliation: 2
    orcid: 0000-0001-7883-3756
  - name: Yasunori Yamamoto
    affiliation: 2
    orcid: 0000-0002-6943-6887
  - name: Pjotr Prins
    orcid: 0000-0002-8021-9162
    affiliation: 3
  - name: Nada Amin
    affiliation: 4
    orcid: 0000-0002-0830-7248
  - name: Deepak Unni
    affiliation: 5
    orcid: 0000-0002-3583-7340
  - name: <nobr>William&nbsp;E.&nbsp;Byrd</nobr>
    affiliation: 6
    orcid: 0000-0003-4730-5293
affiliations:
  - name: Environmental Genomics and Systems Biology, Lawrence Berkeley National Laboratory, Berkeley, CA, USA
    index: 1
  - name: Database Center for Life Science, Research Organization of Information and Systems, Japan
    index: 2
  - name: Department of Genetics, Genomics and Informatics, The University of Tennessee Health Science Center, Memphis, TN, USA.
    index: 3
  - name: Harvard University, USA
    index: 4
    ror: 03vek6s52
  - name: Berkeley Lab, USA
    index: 5
  - name: University of Alabama at Birmingham, USA
    index: 6
date: 3 March 2020
cito-bibliography: paper.bib
event: Fukuoka2019
biohackathon_name: "NBDC/DBCLS BioHackathon"
biohackathon_url:   "http://2019.biohackathon.org/"
biohackathon_location: "Fukuoka, Japan, 2019"
group: Logic programming group
# URL to project git repo --- should contain the actual paper.md:
git_url: https://github.com/biohackrxiv/bhxiv-gen-pdf
# This is the short authors description that is used at the
# bottom of the generated paper (typically the first two authors):
authors_short: Chris Mungall & Hirokazu Chiba \emph{et al.}
---


<!--

The paper.md, bibtex and figure file can be found in this repo:

  https://github.com/journal-of-research-objects/Example-BioHackrXiv-Paper

To modify, please clone the repo. You can generate PDF of the paper by
pasting above link (or yours) in

  http://biohackrxiv.genenetwork.org/

-->

# Introduction

As part of the one week Biohackathion 2019 in Fukuoka Japan, we formed
a working group on logic programming for the biomedical sciences.
Logic programming is understood by many bioinformaticians when it is
presented in the form of relational SQL queries or SPARQL
queries. More advanced logic programming, however, is underutilized in
bioinformatics.  Prolog, for example, is a high-level programming
language that has its roots in first-order logic or first-order
predicate calculus.  Another example, miniKanren, is an embedded
Domain Specific Language for logic programming. Core miniKanren is
exceptionally simple, with only three logical operators and one
interface operator [@uses_method_in:reasoned2nd].

![Logic programming resolver traverses the solution space to find all matches \label{fig}](./logic-programming.png)

![An SVG example](./test.svg)

The introduction of logic programming is particularly relevant in the
context of multi-model data representations where data can be accessed
in memory as free data structures, but also on disk where data can be
represented as tables, trees (documents), and graphs. In
bioinformatics we can make use of all these different data sources and
have a query engine that can mine them all efficiently.

Logic programming is well-suited for biological research. Essentially,
a researcher writes a number of statements that include variables
representing unknown information.  The logic engine then goes through
the solution space (all data) to find possible matches (see figure
\ref{fig}). Much more detail on the rationale and implementations of
miniKanren and logic programming are well summarized in Byrd's book
\emph{The Reasoned Schemer, Second Edition} [@agreesWith:reasoned2nd], PhD thesis
[@ByrdPhD], and [online](https://www.youtube.com/watch?v=eQL48qYDwp4)
[talks](https://www.youtube.com/watch?v=o3AHnyEf7IE).

The `Logic Programming' working group at the 2019 edition of the
annual Japanese BioHackathon applied logic programming to various problems.
The working group:
\begin{itemize}
\item researched state-of-the-art mapping between graph stores and logic programming;
\item created methods for bridging between SPARQL and in-memory data representations using Prolog;
\item extended the Biolink model;
\item and added Relational Biolink type inference for mediKanren.
\end{itemize}

<!--
# Results
-->

## Research of state-of-the-art logic programming facilities for SPARQL

The working group researched current solutions for combining logic
programming with SPARQL.
[ClioPatria](http://www.semantic-web-journal.net/system/files/swj1074.pdf)
is an in-memory RDF quad-store tightly coupled with SWI-Prolog by Jan
Wielemaker, the main author of SWI-Prolog
[@WielemakerBHO15]. SWI-Prolog is published under a BSD license, and
there even exist bindings for
[ClioPatria and Python](http://wi.hwtk.de/WLP2018/Papers/WLP_2018_paper_4.pdf),
for example, although we were unable to locate the source code. We
think ClioPatria and SWI-Prolog are particularly useful for teaching,
and for (in-memory) semantic web applications. SWI-Prolog comes with
client libraries for SQL and SPARQL queries.

## Accessing biological databases using SPARQLProg

<!--
    State the problem you worked on
    Give the state-of-the art/plan
    Describe what you have done/results starting with The working group created...
    Write a conclusion
    Write up any future work
-->

A number of biological databases make their data available in RDF
format, supporting SPARQL access---for example,
[Uniprot](https://www.uniprot.org/),
[NCBI Pubchem](https://pubchemdocs.ncbi.nlm.nih.gov/rdf) and the
[EBI RDF platform](https://www.ebi.ac.uk/rdf/).
SPARQL provides a subset of what logic programming can do.
However, SPARQL queries lack the property of composability and there is no way to
reuse modular components across queries.  For example, to execute a
range query on a genomic region using the FALDO model [@agreesWith:Bolleman2016]
requires authoring a complex query over many triples. If we then wish
to reuse parts of that query in a more complex query, we have to
manually compose them together.

The working group added codes to
[SPARQLProg](https://github.com/cmungall/sparqlprog) which provides a
way to define modular query components using logic programming.
SPARQLProg is written in
SWI-Prolog and has a Python interface library. All code has been made
available in the example directory of
SPARQLProg which provides
sophisticated mapping of logic queries to SPARQL.

For example, a 4-part predicate `feature_in_range` can be composed
with a binary \
`has_mouse_ortholog` predicate:

```
    feature_in_range(grch38:"X", 10000000, 20000000, HumanGene),
    has_mouse_ortholog(HumanGene, MouseGene)
```

This will compile down to a more complex SPARQL query, and execute it against a remote endpoint.

SPARQLProg now includes bindings for many common biological SPARQL
endpoints. As part of this hackathon we developed codes to access RDF
databases of MBGD [@Chiba2015], KEGG OC, TogoVar, JCM, Allie, EBI
 BioSamples, UniProt, and DisGeNET [@Queralt2016]. Future work includes using these
Prolog codes as building blocks for integrative analysis.

## Extending the Biolink Model

<!--
    State the problem you worked on
    Give the state-of-the art/plan
    Describe what you have done/results starting with The working group created...
    Write a conclusion
    Write up any future work
-->

The [Biolink Model](https://github.com/biolink/biolink-model) (see
above) is a data model developed for representing biological and
biomedical knowledge. It includes a schema and generated objects for
the data model and upper ontology. The BioLink Model was designed with
the goal of standardizing the way information is represented in a
graph store, regardless of the formalism used. The working group
focused on extending this model to support representation of a wide
variety of knowledge.

The following tasks were accomplished as part of the BioHackathon:

\begin{enumerate}
\item Represent datasets and their related metadata
\item Represent family and pedigree information to support clinical knowledge
\item Make the provenance model more rich and descriptive
\end{enumerate}

(note the list is written in embedded LaTeX)

For future work, the group will ensure that the new classes added to
the model will have appropriate mappings to other schemas and
ontologies.

##  Relational Biolink type inference for mediKanren

<!--
    State the problem you worked on
    Give the state-of-the art/plan
    Describe what you have done/results starting with The working group created...
    Write a conclusion
    Write up any future work

* Remote member Nada Amin, Chris Mungall, Deepak Unni, Will Byrd

-->

miniKanren is an embedded Domain Specific Language for logic
programming.  The goal was to implement a relational type inferencer
for the [Biolink Model](https://biolink.github.io/biolink-model/) in
miniKanren, which can be integrated into mediKanren. The working group
added a `yaml` subdirectory to the mediKanren GitHub page, and created
multiple files in https://github.com/webyrd/mediKanren/yaml where
`yaml2sexp.py` generates the `biolink.scm` file which contains an
s-expression version of the Biolink yaml file. `yaml.scm` contains
miniKanren relations, and Chez Scheme code that generates miniKanren
relations based on `biolink.scm`. These are giant miniKanren `conde`
clauses that can be thought of as relational tables.  `yaml.scm` also
contains tests for the relations.

Future work includes:

1. integrating this work into the Racket mediKanren code;
2. integrating with the data categories in the KGs;
3. and creating query editor with decent type error messages, autocompletion,
   query synthesis, etc.

# Discussion

The working group concluded that there is ample scope for logic
programming in bioinformatics. Future work includes expansion of
accessing semantic web databases using SPARQLProg, expanding the
BioLink model, and adding dynamic SPARQL support to miniKanren.

## Acknowledgements

We thank the organizers of the NBDC/DBCLS BioHackathon 2019 for
travel support for some of the authors.

## Supplemental information

We use pandoc flavoured markdown, similar to Rstudio see \url{https://garrettgman.github.io/rmarkdown/authoring_pandoc_markdown.html}.

## Tables and figures

Tables can be added in the following way, though alternatives are possible:

| Header 1 | Header 2 |
| -------- | -------- |
| item 1 | item 2 |
| item 3 | item 4 |

Table: Note that table caption is automatically numbered.


| Term                | MB tools/ontologies using this term | Frequency on Biology Stack Exchange | Search Term         |
|:-------------------:|:-----------------------------------:|:-----------------------------------:|:-------------------:|
| Part | iGEM | 9065 | part + parts |
| Component           | SBOL, SBOLDesigner, SBOLCanvas      | 2163                                | component           |
| Module              | SBOL                                | 311                                 | module              |
| Device              |                                     | 677                                 | device              |
| System              |                                     | 16098                               | system              |
| RBS                 |                                     | 548                                 | rbs                 |
| Ribosome Entry Site | SO                                  | 8                                   | ribosome entry site |

LaTeX table:



\begin{tabular}{|l|l|}\hline
Age & Frequency \\ \hline
18--25  & 15 \\
26--35  & 33 \\
36--45  & 22 \\ \hline
\end{tabular}

## Mermaid graphs

This is an example of embedding a graph

```mermaid
graph TD;
    A-->B;
    A-->C;
    B-->D;
    C-->D;
```

Unfortunately it does not work without the mermaid plugin and that requires headless chrome(?!). If you run the command line version of `gen-pdf` it may be possible to get it to work with the right packages. Please tell us if you succeed.

## References
