npm install
for project in $(ls projects/); do
  cd projects/$project
  npm install
  cd ../..
  ng test --project=$project --watch=false --browsers=ChromeHeadless
done
