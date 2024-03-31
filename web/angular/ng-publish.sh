npm install
for project in $(ls projects/); do
  cd projects/$project
  npm install
  ng build
  cd ../..
  cd dist/$project
  npm link
  cd ../..
  ng test --project=$project --watch=false --browsers=ChromeHeadless
  cd projects/$project
  npm publish --access public
  cd ../..
done

