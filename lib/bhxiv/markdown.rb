# Methods to parse the markdown header

require 'yaml'

class MarkdownError < StandardError
end

def md_parser fn
  yml = YAML.load_file(fn)
  yml
end

def meta_expand(header,event)
  meta = header
  meta
end

def md_meta_checker(meta)
  raise MarkdownError,"title field is missing" if not meta["title"]
  raise MarkdownError,"date field is missing" if not meta["date"]
  raise MarkdownError,"bibliography field is missing" if not meta["cito-bibliography"]
  meta
end

def md_checker fn
  header = md_parser(fn)
  md_meta_checker(header)
end
