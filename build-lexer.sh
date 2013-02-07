#!/bin/bash

IDEA_COMMUNITY_PATH=/home/ladicek/work/intellij-community

cd $(dirname $(readlink -f $0))

$IDEA_COMMUNITY_PATH/tools/lexer/jflex-1.4/bin/jflex                         \
  --charat --skel $IDEA_COMMUNITY_PATH/tools/lexer/idea-flex.skeleton        \
  -d gen/cz/ladicek/intellifrog/parser                                       \
  src/cz/ladicek/intellifrog/parser/frog.flex
