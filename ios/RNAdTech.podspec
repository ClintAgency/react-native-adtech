
Pod::Spec.new do |s|
  s.name         = "RNAdTech"
  s.version      = "1.0.0"
  s.summary      = "RNAdTech"
  s.description  = <<-DESC
                  RNAdTech
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  s.author             = { "author" => "alenoir@clintagency.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://gitlab.com/clintagency/antenne-reunion/linfore/linfore-app.git", :tag => "master" }
  s.source_files  = "./**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"

end

  