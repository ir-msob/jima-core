## Root
npm install

## Core Commons
cd projects/core-commons
npm install
cd ../..

ng build core-commons

cd dist/core-commons
npm link
npm publish --access public
cd ../..

## Core Restful
cd projects/core-restful
npm link @ir-msob/jima-core-commons@1.1.17
npm install
cd ../..

ng build core-restful

cd dist/core-restful
npm link
npm publish --access public
cd../..

