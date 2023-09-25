#!/bin/bash
test $(curl localhost:8765/sum?a=2\&b=2) -eq 4
