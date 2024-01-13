npm install
for project in $(ls projects/); do
  cd projects/$project
  npm install
  npm update $PUBLISH_REGISTRY
#  npm unpublish @{ORG}/$project $PUBLISH_REGISTRY -f
  cd ../..
  npm test --project=$project
  ng build --project=$project
  cd dist/$project
  npm publish $PUBLISH_REGISTRY
  cd ../..
done

