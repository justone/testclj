; 1. run 'lein repl' in another tmux tab
; 2. run 'cpp' on each of these lines to start dev 
; 3. run 'cpr' in any module to reload for testing
(require 'testclj.core)
(use 'ring.util.serve)
(serve testclj.core/app)
