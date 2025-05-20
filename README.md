# Generate PDF for VHP4Safety Compound Wiki

We use pandoc with LaTeX templates to generate the PDF from markdown.
This system is based on the work by BioHackrXiv (https://biohackrxiv.org/).

# Introduction


Here you find the required steps to run to code on your own. There is also a [dockerized version](#run-via-docker).

If you find any bugs, please propose improvements via PRs. Thanks!

# Prerequisites

- ruby
- pandoc
- pandoc-citeproc
- pdflatex
- biblatex

# Install

Clone this git repository and install the prerequisites listed above

# Run

Generate the PDF with

```shell
groovy createMarkdown.groovy Q1
ruby ./bin/gen-pdf --debug ./example/cmp VHP Q1.tex
ls -l example/cmp/Q1.tex
cd example/cmp
lualatex Q1.tex
biber Q1.tex
```

Note that the svg figure may complain and other things. Just hit enter, that seems to be fine.
Alternatively, use:

```shell
lualatex -interaction nonstopmode Q1.tex
```

To test the bib file you can try

```
pandoc-citeproc --bib2json paper.bib
```

and you can check the JSON records.
